package graphql.sample


import graphql.sample.model.State
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import groovy.transform.CompileStatic

import javax.inject.Singleton

@Singleton
@CompileStatic
class StateFetcher implements DataFetcher<Iterable<State>> {
    static List states = [
        new State(id:"78d6f05a-efb1-4ab5-aadf-a69b2e2e093f", name: "RS"),
        new State(id:"78d6f05a-efb1-4ab5-aadf-a69b2e2e093d", name: "SC")
    ]

    @Override
    Iterable<State> get(DataFetchingEnvironment environment) throws Exception {
        states
    }
}

