import static ratpack.groovy.Groovy.ratpack

ratpack{
	handlers{
		get('canaryRun'){
			render 'true'
		}

//		http://localhost:5060/canaryRun/some
		get("canaryRun/:name"){
			render "I am person with name \'${pathTokens.name}\' and your name is"
		}

//		http://localhost:5060/name=neha
		//This will grab all the characters after :5060/
//		get(":name") {
//			render "Hello ${pathTokens.getOrDefault('name', 'all')}!"
//		}

		path('profiles/:username?') {
			render pathTokens.getOrDefault('username', 'all')
		}

		//the parameters we pass in parameters can be read through request.queryParams.
//		http://localhost:5060/queryParams?firstname=neha&lastname=gupta
		get("queryParams"){
			render "The query params are "+request.queryParams
		}

		path("fooMethods") {
			byMethod {
				get {
					render "Hello, Foo Get!"
				}
				post {
					render "Hello, Foo Post!"
				}
			}
		}
	}

}