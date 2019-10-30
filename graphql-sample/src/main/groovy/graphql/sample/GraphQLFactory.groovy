package graphql.sample

import graphql.GraphQL
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import groovy.transform.CompileStatic
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.core.io.ResourceResolver
import javax.inject.Singleton

@Factory
@CompileStatic
class GraphQLFactory {

    @Bean
    @Singleton
    GraphQL graphQL(
        ResourceResolver resourceResolver,
        CountryFetcher countryFetcher,
        StateFetcher stateFetcher
    ) {

        def schemaParser = new SchemaParser()
        def schemaGenerator = new SchemaGenerator()

        // Parse the schema.
        def typeRegistry = new TypeDefinitionRegistry()
        typeRegistry.merge(schemaParser.parse(new BufferedReader(new InputStreamReader(
                resourceResolver.getResourceAsStream("classpath:schema.graphqls").get()))))

        // Create the runtime wiring.
        def runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", { typeWiring -> typeWiring
                        .dataFetcher("countries", countryFetcher)
                        .dataFetcher("states", stateFetcher)
                }).build()

        // Create the executable schema.
        def graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)

        // Return the GraphQL bean.
        GraphQL.newGraphQL(graphQLSchema).build()
    }
}