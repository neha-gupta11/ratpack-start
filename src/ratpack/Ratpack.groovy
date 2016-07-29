import static ratpack.groovy.Groovy.ratpack
import ratpack.exec.Blocking

ratpack{
	handlers{
		get('async1') {
			println "async 1111"
			Blocking.get {
				println "222"
				new File("../ratpack-start/build.gradle").text // returns string
			}then {
				println "44"
				render it // it is the return of getValueFromDb()
				println "555"
			}
			println "666"
		}

		get('async2') {
			def l = []
			println "block 1"
			Blocking.get {
				return Thread.start {
					println "Thread 1"
					sleep 3000
				}
			} then {
				l << it
//				Thread[Thread-n,5,main]
			}
			Blocking.get {
				return Thread.start {
					println "Thread 2"
					sleep 2000
				}
			} then {
				l << it
			}
			Blocking.get {
				return Thread.start {
					println "Thread 3"
					sleep 1000
				}
			}then {
				l << it
				render l.join(",")
			}
		}

		prefix("channel") {
			get {
				println "The channel"
				Blocking.get {
					sleep 1000
					println "something is here"
				} then {
					render it+">>>>>>>>>>"
				}
			}
		}
	}

}