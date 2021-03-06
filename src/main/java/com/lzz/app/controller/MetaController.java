package com.lzz.app.controller;

import com.lzz.app.logic.MetaLogic;
import com.lzz.app.model.MetaInfo;
import com.lzz.app.model.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gl49 on 2018/7/6.
 */
@Controller
@RequestMapping("/meta")
public class MetaController {
    @Resource
    private MetaLogic metaLogic;

    @RequestMapping(value = "/getTaskNodes", method = RequestMethod.GET)
    @ResponseBody
    public Response getTaskNodes(){
        String taskNodes = metaLogic.getTaskNodes();
        return Response.Result(Response.DEFAULT, taskNodes);
    }

    @RequestMapping(value = "/getMetaInfoList", method = RequestMethod.GET)
    @ResponseBody
    public Response getMetaInfoList(){
        List<MetaInfo> listMetaInfo = metaLogic.getMetaInfoList();
        return Response.Result(Response.DEFAULT, listMetaInfo);
    }

    @RequestMapping(value = "/getMetaInfo", method = RequestMethod.GET)
    @ResponseBody
    public Response getMetaInfo(@RequestParam int id){
        MetaInfo metaInfo = metaLogic.getMetaInfo(id);
        return Response.Result(Response.DEFAULT, metaInfo);
    }

    @RequestMapping(value = "/removeMetaInfo", method = RequestMethod.GET)
    @ResponseBody
    public Response removeMetaInfo(@RequestParam int id){
        boolean res = metaLogic.removeMetaInfo( id );
        return Response.Result(Response.DEFAULT, res);
    }

    @RequestMapping(value = "/addMetaInfo", method = RequestMethod.POST)
    @ResponseBody
    public Response addMetaInfo(@RequestBody MetaInfo metaInfo){
        boolean res = metaLogic.addMetaInfo( metaInfo );
        return Response.Result(Response.DEFAULT, res);
    }

    @RequestMapping(value = "/updateStartSwitch", method = RequestMethod.GET)
    @ResponseBody
    public Response updateStartSwitch(@RequestParam int id, @RequestParam int startSwitch){
        boolean res = metaLogic.updateStartSwitch( id, startSwitch );
        return Response.Result(Response.DEFAULT, res);
    }


    @RequestMapping(value = "/checkMetaInfo", method = RequestMethod.POST)
    @ResponseBody
    public Response checkMetaInfo(@RequestBody MetaInfo metaInfo){
        boolean checkTopicres = metaLogic.checkKafkaTopic( metaInfo.getKafkaAddress(), metaInfo.getKfakaTopic() );
        boolean hbaseZkRes = metaLogic.checkZookeeperAddress( metaInfo.getZkAddress() );
        if( checkTopicres && hbaseZkRes){
            return Response.Success();
        }else{
            if( !hbaseZkRes ){
                return Response.Error("check hbase zookeeper address");
            }
            if( !checkTopicres ){
                return Response.Error("check kafkaTopic or kafkaAddress");
            }
            return Response.Error("check meta");
        }
    }
}
