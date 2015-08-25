package com.borjabares.myshoppinglist.util;

import java.lang.reflect.Method;
import java.util.*;

public class Expander<E> {
    private static Random random = new Random();
    Map<String, String> propertyAlias;
    List<ExpandNode> nodes;
    private Set<String> takenAliases;

    @SuppressWarnings("unchecked")
    public Expander(String expand, Class<E> entityClass) {
        takenAliases = new HashSet<>();
        propertyAlias = new HashMap<>();
        nodes = new ArrayList<>();
        List<String> properties = Arrays.asList(expand.split(","));
        Collections.sort(properties);

        takenAliases.add("e");

        for (String property : properties) {
            try {
                nodes.add(new ExpandNode(entityClass, property));
            } catch (NoSuchMethodException ignored) {}
        }
    }

    public String getJoins() {
        String joins = "";
        for (ExpandNode node : nodes) {
            joins += " LEFT JOIN FETCH " + node.parentAliasName + "." + node.property + " " + node.alias + " ";
        }
        return joins;
    }

    private class ExpandNode {
        private String parentAliasName;
        private String property;
        private String alias;

        public ExpandNode(Class<?> clazz, String query) throws NoSuchMethodException {
            parentAliasName = "e";

            List<String> properties = Arrays.asList(query.split("\\.", 2));
            property = properties.get(0);
            Method method = clazz.getDeclaredMethod(property, (Class<?>[]) null);

            for (int count = 0; count < 1000; count++) {
                alias = property.substring(0, 3) + String.format("%3d", random.nextInt(1000));
                if (!takenAliases.contains(alias)) {
                    break;
                }
                if (count == 999) {
                    throw new NoSuchMethodException();
                }
            }

            takenAliases.add(alias);
            propertyAlias.put(property, alias);

            if (properties.size() > 1) {
                nodes.add(new ExpandNode(method.getReturnType(), property, properties.get(1)));
            }
        }

        public ExpandNode(Class<?> clazz, String parentPropertyName, String query) throws NoSuchMethodException {
            this(clazz, query);
            this.parentAliasName = propertyAlias.get(parentPropertyName);
        }

        public String getParentAliasName() {
            return parentAliasName;
        }

        public String getProperty() {
            return property;
        }

        public String getAlias() {
            return alias;
        }
    }
}

