var checkTemplete = '<div class="inc-item switch-{startSwitch}" id="{id}">' +
                   '<span class="glyphicon glyphicon-remove-circle remove-inc-item" data-id="{id}"></span>' +
                   "<div class='pull-left inc-item-content' data-detail='{detail}'>" +
                   '<div class="show-object-name" id="inc-item-{id}">[ {incObjectName} ]</div>' +
                   '<div class="show-field"><span class="show-field-name">TableName:</span> {tableName}</div>' +
                   '<div class="show-field"><span class="show-field-name">HbaseZK:</span> {zkAddress}</div>' +
                   '<div class="show-field"><span class="show-field-name">Topic:</span> {kfakaTopic}</div>' +
                   '<div class="show-field"><span class="show-field-name">Kafka:</span> {kafkaAddress}</div>' +
                   '</div>'+
                   '<div class="btn-group btn-group-xs pull-left" role="group" style="margin-left: 10px;margin-top: 5px;">' +
                   '<button type="button" class="btn btn-success start-task" data-id="{id}">Start</button>'+
                   '<button type="button" class="btn btn-default stop-task" data-id="{id}">Stop</button>'+
                   '</div>' +
                   '</div>';

var alertTemplate = '<div class="alert alert-danger" role="alert">' +
                    '<strong>Error!</strong> {msg}' +
                    '</div>';

$(document).ready(function(){
    $.get("/meta/getTaskNodes", function(obj){
        var taskNodes = obj.res;
        $("#service-node").text( taskNodes );
    });
    $.get("/meta/getMetaInfoList", function(obj){
        var metaList = obj.res;
        var div_con = "";
        $.each( metaList, function(i, checkobj){
            checkobj.detail = JSON.stringify(checkobj);
            div_con += checkTemplete.format( checkobj );
        });
        $("#add-inc-item").before( div_con );
    });
});

$(document).on("click",".inc-item-content", function(){
    var detail = $(this).data("detail");
    detail.updateTime = formatDate( detail.updateTime );
    $("#inc-item-detail").html( syntaxHighlight(detail) );
    $('#detail-inc-item-model').modal('show');
});

$(document).on("click", "#add-inc-item", function(){
    $("#add-inc-item-model").modal("show");
});


$(document).on("click",".remove-inc-item", function(){
    var id = $(this).data("id");
    $("#remove-inc-item-confirm").data("id", id );
    $("#remove-inc-item-show-id").text( $("#inc-item-" + id).text() );
    $('#remove-inc-item-model').modal('show');
});

$(document).on("click",".start-task", function(){
    var id = $(this).data("id");
    $.get("/meta/updateStartSwitch?id=" + id + "&startSwitch=1", function(data){
        location.reload();
    });
});
$(document).on("click",".stop-task", function(){
    var id = $(this).data("id");
    $.get("/meta/updateStartSwitch?id=" + id + "&startSwitch=0", function(data){
        location.reload();
    });
});


$("#remove-inc-item-confirm").click(function(){
    $('#remove-inc-item-model').modal('hide');
    var id = $("#remove-inc-item-confirm").data( "id" );
    $.get("/meta/removeMetaInfo?id=" + id, function(data){
        $( "#" + id ).remove();
    });
});

$("#add-inc-item-confirm").click(function(){
    var data = {};
    data.incObjectName= $("input[name='incObjectName']").val().trim();
    if( !data.incObjectName ){
        addError("incObjectName is not empty");
        return;
    }
    data.tableName= $("input[name='tableName']").val().trim();
    if( !data.tableName ){
        addError("tableName is not empty");
        return;
    }
    data.familyColumn= $("input[name='familyColumn']").val().trim();
    if( !data.familyColumn ){
        addError("familyColumn is not empty");
        return;
    }
    data.zkAddress= $("input[name='zkAddress']").val().trim();
    if( !data.zkAddress ){
        addError("zkAddress is not empty");
        return;
    }
    data.kafkaAddress= $("input[name='kafkaAddress']").val().trim();
    if( !data.kafkaAddress ){
        addError("kafkaAddress is not empty");
        return;
    }
    data.kfakaTopic= $("input[name='kfakaTopic']").val().trim();
    if( !data.kfakaTopic ){
        addError("kfakaTopic is not empty");
        return;
    }
    data.maxUpdateRangeTime= $("input[name='maxUpdateRangeTime']").val().trim();
    if( !data.maxUpdateRangeTime ){
        addError("maxUpdateRangeTime is not empty");
        return;
    }
    data.updateRangeTime= $("input[name='updateRangeTime']").val().trim();
    if( !data.updateRangeTime ){
        addError("updateRangeTime is not empty");
        return;
    }

    post("/meta/checkMetaInfo", data, function( re, arg ){
       if( re.code == 0 ){
          post("/meta/addMetaInfo", data, function( re, arg ){
             location.reload();
          });
       }else{
         addError( re.msg );
       }
    });


});

function addError(msg){
    $("#add-inc-item-alert").html( alertTemplate.format( {"msg": msg } ) );
}


function formatDate(time){
    var date = new Date(time);
    var year = date.getFullYear(),
        month = date.getMonth() + 1,
        day = date.getDate(),
        hour = date.getHours(),
        min = date.getMinutes(),
        sec = date.getSeconds();
    var newTime = year + '-' +
                month + '-' +
                day + ' ' +
                hour + ':' +
                min + ':' +
                sec;
    return newTime;
}

function post(url, data, callback){
    $.ajax({
      type: 'POST',
      url: url,
      data: JSON.stringify(data),
      dataType: "json",
      contentType: 'application/json',
      async:false,
      success: callback
    });
}


function syntaxHighlight(json) {
    if (typeof json != 'string') {
        json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&');
    json = json.replace(/</g, '<');
    json = json.replace(/>/g, '>');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
        var cls = 'number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'key';
            } else {
                cls = 'string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'boolean';
        } else if (/null/.test(match)) {
            cls = 'null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}



String.prototype.format = function(args) {
    var result = this;
    if (arguments.length > 0) {
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                if(args[key]!=undefined){
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] != undefined) {
                    var reg = new RegExp("({[" + i + "]})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
}
