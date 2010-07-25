/*
 * Copyright (c) 2010. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.axonframework.integrationtests.commandhandling;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;

import java.util.UUID;

/**
 * @author Allard Buijze
 */
public class StubAggregate extends AbstractAnnotatedAggregateRoot {

    private int changeCounter;

    static StubAggregate create(UUID identifier) {
        StubAggregate aggregate = new StubAggregate(identifier);
        aggregate.apply(new StubAggregateCreatedEvent());
        return aggregate;
    }

    StubAggregate(UUID identifier) {
        super(identifier);
    }

    public void makeAChange() {
        apply(new StubAggregateChangedEvent());
    }

    @EventHandler
    private void onCreated(StubAggregateCreatedEvent event) {
        changeCounter = 0;
    }

    @EventHandler
    private void onChange(StubAggregateChangedEvent event) {
        changeCounter++;
    }
}