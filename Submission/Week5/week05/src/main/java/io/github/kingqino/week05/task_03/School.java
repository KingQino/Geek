package io.github.kingqino.week05.task_03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class School {
    private final String name;
    private final List<Klass> klasses;
}
