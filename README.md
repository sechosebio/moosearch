# moosearch
Solr + AngularJS: MOOC search engine


#### Execute

##### Execute search engine 
In [solr/example](https://github.com/sechosebio/moosearch/tree/master/solr/example/) run `java -Dsolr.solr.home=solr -jar start.jar;`

- Solr admin page: http://localhost:8983/solr/#/collection1/query
- [Web interface](https://github.com/sechosebio/moosearch/tree/master/solr/example/solr-webapp/webapp/web) for search engine: http://localhost:8983/solr/web/index.html#

##### Execute indexer
Compile and execute the mvn project of the [indexer folder](https://github.com/sechosebio/moosearch/tree/master/indexador), you have to have solr running.
