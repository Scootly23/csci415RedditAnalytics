HADOOP_CLASSPATH=$(shell hadoop classpath)

postElection: redditAnalytics.java redditAnalyticsMapper.java redditAnalyticsReducer.java
	mkdir redditAnalytics
	javac -classpath $(HADOOP_CLASSPATH) -d redditAnalytics/ redditAnalytics.java redditAnalyticsReducer.java redditAnalyticsMapper.java
	jar -cvf redditAnalytics.jar -C redditAnalytics/ .
	hadoop jar redditAnalytics.jar redditAnalytics /user/micaschm/postOutput.txt redditAnalytics/output
	hdfs dfs -cat redditAnalytics/output/part-r-00000 | less

preElection: redditAnalytics.java redditAnalyticsMapper.java redditAnalyticsReducer.java
	mkdir redditAnalytics
	javac -classpath $(HADOOP_CLASSPATH) -d redditAnalytics/ redditAnalytics.java redditAnalyticsReducer.java redditAnalyticsMapper.java
	jar -cvf redditAnalytics.jar -C redditAnalytics/ .
	hadoop jar redditAnalytics.jar redditAnalytics /user/micaschm/preOutput.txt redditAnalytics/output
	hdfs dfs -cat redditAnalytics/output/part-r-00000 | less

jsonParser: jsonParser.java
	javac -classpath ./org/json/java-json.jar jsonParser.java
	java jsonParser

clean:
	rm -rf redditAnalytics
	rm redditAnalytics.jar
	hdfs dfs -rm -R redditAnalytics


