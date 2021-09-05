package io.github.kingqino.week05.task_08;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "school")
public class SchoolProperties {
    private String name;
    private List<Integer> studentIds;
    private List<String> studentNames;
    private List<String> classNames;
}
