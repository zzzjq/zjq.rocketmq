package zjq.work.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author zhangjq
 * @date 2018年11月26日下午3:16:51
 *
 **/
public class Main {
	public static void main(String[] args) throws Exception{
		ClassPathXmlApplicationContext a = new ClassPathXmlApplicationContext(
				new String[] {"rocketmq/rocketmq.consumer.xml",
						"rocketmq/rocketmq.producer.xml"});	
		a.start();
	}
}
