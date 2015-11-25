package com.borjabares.myshoppinglist.util;

import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Joiner<E> {
    private Tree tree;

    public Joiner(String expand, Class<E> clazz) {
        List<String> properties = Arrays.asList(expand.split(","));
        Collections.sort(properties);

        tree = new Tree("root");

        for (String property : properties) {
            try {
                expandPath(tree, clazz, property);
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

    private void expandPath(Tree tree, Class<?> clazz, String property) throws NoSuchFieldException {
        List<String> path = Arrays.asList(property.split("\\.", 2));

        String currentPath = path.get(0);
        Type type = clazz.getDeclaredField(currentPath).getGenericType();

        if (!tree.haveChild(currentPath)) {
            tree.addChild(currentPath);
        }

        try {
            if (path.size() > 1) {
                if (type instanceof ParameterizedType) {
                    expandPath(tree.getChild(currentPath), (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0], path.get(1));
                } else {
                    expandPath(tree.getChild(currentPath), (Class<?>) type, path.get(1));
                }
            }
        } catch (NoSuchFieldException ignored) {
        }
    }
}
