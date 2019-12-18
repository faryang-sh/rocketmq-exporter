package org.apache.rocketmq.exporter;

import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.protocol.route.BrokerData;
import org.apache.rocketmq.common.protocol.route.TopicRouteData;
import org.apache.rocketmq.exporter.task.MetricsCollectTask;
import org.apache.rocketmq.tools.admin.MQAdminExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author faryangsh@163.com
 * Date    2019-12-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RocketmqLatencyTest {

    @Autowired
    private MetricsCollectTask task;

    @Resource
    private MQAdminExt mqAdminExt;

    @Test
    public void test1() throws Exception {
        String topic = "TopicTest4";
        String group = "cg14";
        TopicRouteData topicRouteData = mqAdminExt.examineTopicRouteInfo(topic);
        BrokerData bd = topicRouteData.getBrokerDatas().get(0);
        String masterAddr =bd.getBrokerAddrs().get(MixAll.MASTER_ID);
        task.collectLatencyMetrcisInner(topic, group, masterAddr, bd);
    }
}
