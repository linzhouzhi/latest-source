var checkTemplete = '<div class="checkobj {healthy}" id="{id}">' +
                   '<span class="glyphicon glyphicon-remove-circle remove-checkobj" data-id="{id}"></span>' +
                   "<div class='pull-left checkobj-content' data-detail='{detail}'>" +
                   '<div class="show-checkid">{id}</div>' +
                   '<img src="{type_image}"> {type}</img>' +
                   '<div class="show-address">{address}</div>' +
                   '<div>{group}</div>' +
                   '</div>' +
                   '</div>';

var icon_hbase = "http://hbase.apache.org/images/favicon.ico";
var icon_redis = "http://www.redis.cn//images/favicon1.png";

var alertTemplate = '<div class="alert alert-danger" role="alert">' +
                    '<strong>Error!</strong> {msg}' +
                    '</div>';

$(document).ready(function(){
    $.get("/node/listCheckObj?group=" + getUrlParam('group'), function(res){
        var nodeList = res.nodeList;
        $("#service-node").text( nodeList.join(" | ") );
        var checkList = res.checkList;
        if( checkList ){
            $.each( checkList, function(i, checkobj){
                checkobj.detail = JSON.stringify(checkobj);
                checkobj = addCheckImage( checkobj);
                if( checkobj.group == "null" || !checkobj.group ){
                    checkobj.group = "";
                }
                $("#add-checkobj").before( checkTemplete.format( checkobj ) );
            });
        }
    });
});


function addCheckImage( checkobj){
    if(checkobj.type=="redis"){
        checkobj.type_image = "../vender/images/favicon-redis.png";
    }else if(checkobj.type=="hbase"){
        checkobj.type_image = "../vender/images/favicon-hbase.ico";
    }else{
        checkobj.type_image = "../vender/images/favicon-test.png";
    }
    return checkobj;
}

$("#add-checkobj").click(function(){
    $('#add-checkobj-model').modal('show');
    $("input[name='id']").val("");
    $("input[name='address']").val("");
    $("input[name='group']").val("");
    $("[name='save_target']").val("");
    $("#add-checker-alert").html("");
});

$("#add-checkobj-confirm").click(function(){
    var data = {};
    data.id= $("input[name='id']").val().trim();
    data.type= $("input[type='radio'][name='type']:checked").val().trim();
    data.address= $("input[name='address']").val().trim();
    data.group= $("input[name='group']").val().trim();
    data.healthy = "HEALTHY";
    var save_target = "";
    try{
      var str = $("[name='save_target']").val().trim();
      save_target = $.parseJSON( str );
    }catch( ignore ){
        save_target = {};
    }
    data.saveTarget= save_target;
    data.detail = JSON.stringify(data);
    if( data.id && data.address ){
        post("/node/checkValid", data, function( res ){
            console.log(res);
            if( res ){
                $("#add-checker-alert").html( alertTemplate.format( {"msg": res } ) );
            }else{
                $('#add-checkobj-model').modal('hide');
                post("/node/addCheckObj", data, function( re, arg ){
                   data = addCheckImage( data);
                   $("#add-checkobj").before( checkTemplete.format( data ) );
                });
            }
        });
    }else{
        $("#add-checker-alert").html( alertTemplate.format( {"msg": "checkid or address not can empty" } ) );
    }
});


$(document).on("click",".checkobj-content", function(){
    var detail = $(this).data("detail");
    console.log( detail );
    $("#checker-detail").html( syntaxHighlight(detail) );
    $('#detail-checkobj-model').modal('show');
});

$(document).on("click",".remove-checkobj", function(){
    var id = $(this).data("id");
    $("#remove-checkobj-confirm").data("id", id );
    $("#remove-checkobj-show-id").text( id );
    $('#remove-checkobj-model').modal('show');
});

$("#remove-checkobj-confirm").click(function(){
    $('#remove-checkobj-model').modal('hide');
    var id = $("#remove-checkobj-confirm").data( "id" );
    $.get("/node/removeCheckObj?id=" + id, function(data){
        $( "#" + id ).remove();
    });

});


function post(url, data, callback){
    $.ajax({
      type: 'POST',
      url: url,
      data: JSON.stringify(data),
      dataType: "text",
      contentType: 'application/json',
      async:false,
      success: callback
    });
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
