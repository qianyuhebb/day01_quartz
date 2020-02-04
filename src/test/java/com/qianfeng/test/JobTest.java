package com.qianfeng.test;

import com.qianfeng.job.MyJob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.GregorianCalendar;

import static java.util.Calendar.MONDAY;

/**
 * 时间：  2020/2/4
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/


public class JobTest {

    public static void main(String[] args) throws SchedulerException {


    }

    @Test
   public void testSimpleScheduleBuilder(){
        try {
            //创建调度器，核心组件
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //创建触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            //创建触发器
            //定义name和group
          /*  Trigger trigger = triggerBuilder.withIdentity("trigger1", "group1")
                    //一旦加入schedule，就立即生效
                    .startNow()
                    //定义触发器类型
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    //触发频率，时间
                    .withIntervalInSeconds(2)
                    .withRepeatCount(2))
                    .build();*/

            Trigger trigger = triggerBuilder.withIdentity("trigger1", "group1")
                    //一旦加入schedule，就立即生效
                    .startNow()
                    //定义触发器类型
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            //触发频率，时间
                            .withIntervalInSeconds(2)
                            .repeatForever())
                    //.endAt(new GregorianCalendar(2020,1,4,19,01,0).getTime())
                    .build();


            //定义job  任务   将 myjob 交给builder
            JobDetail  jobDetail =  JobBuilder.newJob(MyJob.class)
                    .withIdentity("测试任务","test")
                    .usingJobData("data","jobdata").build();
            //将任务和触发器加入到调度器
            scheduler.scheduleJob(jobDetail,trigger);
            //启动调度器
            scheduler.start();
            //Thread.sleep(4000);
            //关闭调度器
            // scheduler.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

   @Test
   public void testCalendarIntervalScheduleBuilder(){
       try {
           //创建调度器，核心组件
           Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
           //创建触发器
           TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
           //创建触发器
           //定义name和group


           Trigger trigger = triggerBuilder.withIdentity("trigger2", "group1")
                   //一旦加入schedule，就立即生效
                   .startNow()
                   //定义触发器类型
                   .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                           //触发频率，时间
                           .withIntervalInSeconds(1)
                          )
                   .endAt(new GregorianCalendar(2020,1,4,20,2).getTime())
                   .build();


           //定义job  任务   将 myjob 交给builder
           JobDetail  jobDetail =  JobBuilder.newJob(MyJob.class)
                   .withIdentity("测试任务","test")
                   .usingJobData("data","jobdata").build();
           //将任务和触发器加入到调度器
           scheduler.scheduleJob(jobDetail,trigger);
           //启动调度器
           scheduler.start();
           //Thread.sleep(4000);
           //关闭调度器
           // scheduler.shutdown();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   @Test
   public void testDailyTimeIntervalScheduleBuilder(){

       try {
           //创建调度器，核心组件
           Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
           //创建触发器
           TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
           //创建触发器
           //定义name和group


           Trigger trigger = triggerBuilder.withIdentity("trigger3", "group1")
                   //一旦加入schedule，就立即生效
                   .startNow()
                   //定义触发器类型
                   .withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                           .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9,0))//每天9点开始
                           .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(18,0))//每天18点结束
                           .onDaysOfTheWeek(2) //周一执行
                           .onEveryDay()//每天执行
                            .withIntervalInHours(1)//每小时执行一次
                            .withRepeatCount(100) //执行100次
                   )
                   //.endAt(new GregorianCalendar(2020,1,4,19,01,0).getTime())
                   .build();


           //定义job  任务   将 myjob 交给builder
           JobDetail  jobDetail =  JobBuilder.newJob(MyJob.class)
                   .withIdentity("测试任务","test")
                   .usingJobData("data","jobdata").build();
           //将任务和触发器加入到调度器
           scheduler.scheduleJob(jobDetail,trigger);
           //启动调度器
           scheduler.start();
           //Thread.sleep(4000);
           //关闭调度器
           // scheduler.shutdown();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
    @Test
   public void testCronScheduler(){
       try {
           //创建调度器，核心组件
           Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
           //创建触发器
           TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
           //创建触发器
           //定义name和group


           Trigger trigger = triggerBuilder.withIdentity("trigger2", "group1")
                   //一旦加入schedule，就立即生效
                   .startNow()
                   //定义触发器类型
                   .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")

                   )
                   //.endAt(new GregorianCalendar(2020,1,4,19,01,0).getTime())
                   .build();


           //定义job  任务   将 myjob 交给builder
           JobDetail  jobDetail =  JobBuilder.newJob(MyJob.class)
                   .withIdentity("测试任务","test")
                   .usingJobData("data","jobdata").build();
           //将任务和触发器加入到调度器
           scheduler.scheduleJob(jobDetail,trigger);
           //启动调度器
           scheduler.start();
           //Thread.sleep(4000);
           //关闭调度器
           // scheduler.shutdown();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
