package org.example.api;

import org.example.boards.Board;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RuleSet<T extends Board> implements Iterable<Rule>{
    Set<Rule> ruleList = new HashSet<>();

    public void add(Rule boardRule){
        ruleList.add(boardRule);
    }

    @Override
    public Iterator<Rule> iterator() {
        return ruleList.iterator();
    }

    @Override
    public void forEach(Consumer<? super Rule> action) {
        ruleList.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return ruleList.spliterator();
    }
}
