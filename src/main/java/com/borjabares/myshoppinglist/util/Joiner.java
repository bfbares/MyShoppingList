package com.borjabares.myshoppinglist.util;

import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Joiner<E> {
    private Tree tree;

    public Joiner(String expand) {
        List<String> properties = Arrays.asList(expand.split(","));
        Collections.sort(properties);

        tree = new Tree("root");

        for (String property : properties) {
            try {
                expandPath(tree, property);
            } catch (NoSuchFieldException ignored) {
            }
        }
    }

    public Root<?> makeJoins(CriteriaQuery<E> query, Class<E> entityClass) {
        Root<?> root = query.from(entityClass);
        join(root, tree);
        return root;
    }

    private void join(FetchParent<?, ?> parent, Tree tree) {
        for (Tree joinTree : tree.getChildren()) {
            Fetch<?, ?> join = parent.fetch(joinTree.getName(), JoinType.LEFT);
            if (joinTree.haveChildren()) {
                join(join, joinTree);
            }
        }
    }

    private void expandPath(Tree tree, String property) throws NoSuchFieldException {
        List<String> path = Arrays.asList(property.split("\\.", 2));

        String currentPath = path.get(0);

        if (!tree.haveChild(currentPath)) {
            tree.addChild(currentPath);
        }

        if (path.size() > 1) {
            try {
                expandPath(tree.getChild(currentPath), path.get(1));
            } catch (NoSuchFieldException ignored) {
            }
        }
    }
}
