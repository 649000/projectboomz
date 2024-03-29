dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "root"
    password = ""
    dbCreate = "update"
    url = "jdbc:mysql://localhost:3306/boomzdb"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
//environments {
//    development {
//        dataSource {
//            dbCreate = "create-drop" // one of 'create', 'create-drop','update'
//            url = "jdbc:hsqldb:mem:devDB"
//        }
//    }
//    test {
//        dataSource {
//            dbCreate = "update"
//            url = "jdbc:hsqldb:mem:testDb"
//        }
//    }
//    production {
//        dataSource {
//            dbCreate = "update"
//            url = "jdbc:hsqldb:file:prodDb;shutdown=true"
//        }
//    }
//}
