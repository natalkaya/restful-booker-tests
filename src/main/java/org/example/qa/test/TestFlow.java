package org.example.qa.test;

public class TestFlow {
    public static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TestFlow.class);
    /**
     * Add extra log information before calling other action(s)
     * @param description the step description
     */
    public static void step(String description) {
        log.info(">>> STEP: " + description);
    }
}
