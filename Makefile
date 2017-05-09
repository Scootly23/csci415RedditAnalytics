HADOOP_CLASSPATH=$(shell hadoop classpath)

reddit: redditAnalytics.java redditAnalyticsMapper.java redditAnalyticsReducer.java
	mkdir redditAnalytics
	javac -classpath $(HADOOP_CLASSPATH) -d redditAnalytics/ redditAnalytics.java redditAnalyticsReducer.java redditAnalyticsMapper.java
	jar -cvf redditAnalytics.jar -C redditAnalytics/ .
	hadoop jar redditAnalytics.jar redditAnalytics /user/micaschm/output.txt redditAnalytics/output
	hdfs dfs -cat redditAnalytics/output/part-r-00000 | less

clean:
	rm -rf redditAnalytics
	rm redditAnalytics.jar
	hdfs dfs -rm -R redditAnalytics


