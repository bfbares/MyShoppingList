package com.borjabares.myshoppinglist.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Tree implements Serializable {
    private static final long serialVersionUID = -76951335208097516L;

    private String name;
    private Map<String, Tree> children;

    public Tree(String name) {
        this.name = name;
        this.children = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addChild(String child) {
        if (!haveChild(child)) {
            this.children.put(child, new Tree(child));
        }
    }

    public Tree getChild(String child) {
        return this.children.get(child);
    }

    public boolean haveChild(String child) {
        return this.children.containsKey(child);
    }

    public boolean haveChildren() {
        return !this.children.isEmpty();
    }

    public Collection<Tree> getChildren() {
        return this.children.values();
    }

}
