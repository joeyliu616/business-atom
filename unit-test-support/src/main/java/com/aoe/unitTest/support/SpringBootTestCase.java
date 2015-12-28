package com.aoe.unitTest.support;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by joey on 15-12-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class SpringBootTestCase {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
