const _ = require("lodash")

async function save(app, entityName, obj){
    try {
        const entity = await app.adapter.find(entityName, {_id: obj._id})
        if (!entity){
            const res = await app.adapter.create(entityName, obj)
            console.log(`Entity created [entity: ${entityName}, response: ${JSON.stringify(res)}`)
        }
    } catch (error){
        console.error(`Unable to process ${entityName} - ${obj.name}`)
    }
}

module.exports = async app => {
    const db = {
        country : [
            { _id: '78d6f05a-efb1-4ab5-aadf-a69b2e2e093f', name : 'Brasil' },
            { _id: '78d6f05a-efb1-4ab5-aadf-a69b2e2e093d', name : 'Argentina' }
        ],
        state : [
            { _id: '68d6f05a-efb1-4ab5-aadf-a69b2e2e093f', name : 'Rio Grande do Sul', links: {country: '78d6f05a-efb1-4ab5-aadf-a69b2e2e093f' }},
            { _id: '68d6f05a-efb1-4ab5-aadf-a69b2e2e093d', name : 'Santa Catarina'   , links: {country: '78d6f05a-efb1-4ab5-aadf-a69b2e2e093f' }}
        ],
        city : [
            { _id: '58d6f05a-efb1-4ab5-aadf-a69b2e2e093f', name : 'Porto Alegre', links: {state: '68d6f05a-efb1-4ab5-aadf-a69b2e2e093f' }},
            { _id: '58d6f05a-efb1-4ab5-aadf-a69b2e2e093d', name : 'Canoas'      , links: {state: '68d6f05a-efb1-4ab5-aadf-a69b2e2e093f' }}
        ]
    }

    _.each ( db, async (values, key) => {
        _.each (values, async value => {
            await save(app, key, value)
        })
    })
}
