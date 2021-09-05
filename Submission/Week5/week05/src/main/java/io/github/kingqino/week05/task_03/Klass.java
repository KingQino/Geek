package io.github.kingqino.week05.task_03;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Klass {
    private final String klassName;

    private final List<Student> students;
}
