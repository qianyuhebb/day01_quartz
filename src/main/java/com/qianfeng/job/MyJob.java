package com.qianfeng.job;

import org.quartz.*;


import java.util.Date;

/**
 * 时间：  2020/2/4
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
//防止并发执行
@DisallowConcurrentExecution
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        try {
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建工作详细
        JobDetail jobDetail = context.getJobDetail();
         //获取工作名称
        String name = jobDetail.getKey().getName();
        String group = jobDetail.getKey().getGroup();
        String data = jobDetail.getJobDataMap().getString("data");
        System.out.println("job执行，job名为："+name+"group为："+group+"data 为："+data+new Date());



    }
}
