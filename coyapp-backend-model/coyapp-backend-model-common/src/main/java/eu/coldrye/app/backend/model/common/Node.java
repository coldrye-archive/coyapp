/*
 * Copyright 2016 coldrye.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.coldrye.app.backend.model.common;

/**
 * TBD models a node with parent and children in a tree like data structure
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @param <T>
 * @since 0.0.1
 */
public interface Node<T extends AbstractUnique>
{

    NodeType getNodeType();

    T getParent();

    void setNodeType(final NodeType nodeType);

    void setParent(final T parent);

    public static enum NodeType
    {
        ROOT, CHILD, LEAF;
    }
}
