# PROCESSING MEETUP REAL-TIME DATA WITH APACHE FLINK

This material is part of my hands-on introductory training for Apache Flink, especially stream API. 

This tutorial analize real-time data from the social network  [www.meetup.com](www.meetup.com). The provided project template includes a Flink Sink that read streaming data and parser it as JAVA *MeetupRSVGevent* object. In the [Meetup API web](https://www.meetup.com/es-ES/meetup_api/docs/stream/2/rsvps/?uri=%2Fmeetup_api%2Fdocs%2Fstream%2F2%2Frsvps%2F) you can find the details about the source data. 

Details about the ```MeetupRSVGevent``` class can be found in the provided [project template](https://github.com/rubencasado/meetup/blob/master/src/main/java/es/flink/meetupStreaming/jsonparser/MeetupRSVGevent.java).

For details about what is Apache Flink, how it works and how to use de DataStream and Stream SQL, please refer to the slides provided in the course and the official [Apache Flink documentation](https://ci.apache.org/projects/flink/flink-docs-stable/).

## 1. Clone the repository

```
git clone -b training --single-branch https://github.com/rubencasado/meetup.git
```

## 2. Import the project in your IDE
Import as MAVEN project using the ```pom.xml``` file you have cloned

![](.README_images/import1.png)

## 3. Run the project in your IDE
```Main.java``` is the entry point for the project. You should see in your IDE terminal real-time events

![](.README_images/RunJavaProyect.png)
![](.README_images/output.png)

## 4. Use DataStream API

Do the next actions:

1. Delete *bad events* (any of its fields is null) 
2. Count users confirmed for each event each 10 seconds
3. Count users confirmed for each event in the last 20 seconds updating the result each 5 seconds
4. Calculate the Trending Topics (hot words after deleting stop words) based on the topic_name. Analyze the last 60 seconds information and update the results each 10 seconds
5. Calculate in real-time the number of confirmed user for country taking into account all-time data

## 4. Use the SQL Stream API
Try to response the 2 and 3 of the above questions using the SQL API. Some tips:

```java
StreamTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);
```

```java
tableEnv.registerDataStream("MyTableName", events, "all fields, ..., UserActionTime.proctime");
```

```java
String query = "SELECT ...";
Table table = tableEnv.sqlQuery(query);
DataStream<Row> sql_result = tableEnv.toAppendStream(table, Row.class);
```

## 5. EXTRA: write to Elasticsearch (and view results in Kibana)
- Install *ElasticSearch* and *Kibana*
- Run ElasticSearch and Kibana
- Write a Flink Sink to write to Elasticsearch. Some tips
```java
List<HttpHost> httpHosts = new ArrayList<>();
httpHosts.add(new HttpHost(hostname, port, schema));
         
ElasticsearchSink.Builder<Row> myEsSinkBuilder = new ElasticsearchSink.Builder<>(httpHosts,
     new ElasticsearchSinkFunction<Row>() {
        public IndexRequest createIndexRequest(Row element) {
            Map<String, String> json = new HashMap<>();
            for (int i=0; i< element.getArity(); i++)
                json.put("field"+i,element.getField(i).toString());
            return Requests.indexRequest()
                   .index(index)
                   .type(type)
                   .source(json);

            }
            
            @Override
            public void process(Row element, RuntimeContext ctx, RequestIndexer indexer) {
                         indexer.add(createIndexRequest(element));
                     }
            }
         );

myEsSinkBuilder.setBulkFlushMaxActions(1);
stream.addSink(myEsSinkBuilder.build());```
```
- Open Kibana, create a dashboard with your real-time data!


