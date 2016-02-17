package com.github.nachomezzadra.redistest;

import com.github.nachomezzadra.redistest.config.ApplicationConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public abstract class BaseSpringTest {

}
