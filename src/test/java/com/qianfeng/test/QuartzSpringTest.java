package com.qianfeng.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 时间：  2020/2/4
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:quartz_applicationContext.xml")
public class QuartzSpringTest {

    @Autowired
    private StdScheduler stdScheduler;

    @Test
    public void test() throws Exception {

        System.out.println("-****************");
        Thread.sleep(10000);

        //删除job
             //暂停触发器的计时
        stdScheduler.pauseTrigger(TriggerKey.triggerKey("trigger01","group01"));
          //移除触发器中的任务
       stdScheduler.unscheduleJob(TriggerKey.triggerKey("trigger01","group01"));
         //移除jtrigger 以后 删除job
       stdScheduler.deleteJob(JobKey.jobKey("trigger01","group01"));


       //job的暂停和恢复 单个
        stdScheduler.pauseJob(JobKey.jobKey("job1","group1"));

        stdScheduler.resumeJob(JobKey.jobKey("job1","group1"));

        //暂停和恢复group中的所有job
         GroupMatcher<JobKey> groups = GroupMatcher.groupEquals("group1");
         stdScheduler.pauseJobs(groups);
         stdScheduler.resumeJobs(groups);

    }
}
