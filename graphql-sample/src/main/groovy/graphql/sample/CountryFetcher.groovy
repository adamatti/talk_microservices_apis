package graphql.sample

import graphql.sample.model.Country
import graphql.sample.model.State
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import groovy.transform.CompileStatic

import javax.inject.Singleton

@Singleton
@CompileStatic
class CountryFetcher implements DataFetcher<Iterable<Country>>{
    private static List<Country> countries = [
        new Country(id: '78d6f05a-efb1-4ab5-aadf-a69b2e2e093f', name : 'Brasil - Groovy', states: StateFetcher.states),
        new Country(id: '78d6f05a-efb1-4ab5-aadf-a69b2e2e093d', name : 'Argentina - Groovy')
    ]

    @Override
    Iterable<Country> get(DataFetchingEnvironment env) {
        def criteria = [
            id : env.getArgument("id") as String
        ]
        filter(criteria,countries)
    }

    // TODO this method need to be improved
    private static List filter(Map<String,String> filter, List<Country> values){
        if (filter.id){
            return values.findAll { row ->
                filter.id.contains(row.id)
            }
        }
        values
    }
}

