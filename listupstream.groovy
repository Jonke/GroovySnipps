def hi = hudson.model.Hudson.instance
hi.getItems(hudson.model.Project).each {project ->

  if (project.displayName== "TestTop"){
   println(project.displayName)
   def di = project.getUpstreamProjects()
   println("up")
     for ( x in di ) {
      def csm = x.getScm()
      def a = csm.getApi()
      println(x.name)
      println(csm.getType())
      hg =  csm
     


      hi.copy(x, "Copy"+x.name); 
      def newjob=hi.getItem("Copy"+x.name)
      def triggers=newjob.getBuildTriggerUpstreamProjects()
      for (z in triggers){
          println(z.getDisplayName())
      }
      if ("hudson.plugins.mercurial.MercurialSCM" == csm.getType()){
         def newscm = newjob.getScm()
         def installation = newscm.getInstallation()
         def source = newscm.getSource()
         def branch= "branch" //newscm.getBranch()
         def modules= ""
         def subdir= newscm.getSubdir()
         def browser = newscm.getBrowser()
         def clean = newscm.isClean()
         def newscm2 = new hudson.plugins.mercurial.MercurialSCM(installation, source, branch,modules,subdir,browser,clean)
         newjob.setScm(newscm2)    
      }

       if ("hudson.scm.SubversionSCM" == csm.getType()){
         def newscm = newjob.getScm()
        def svnUrls =  newscm.getLocations()
       
        println(svnUrls)
        def urls = []
        def locals =[]
        for(y in svnUrls){
          println(y.getURL() + " " + y.getLocalDir())
           urls.add(y.getURL()+ "/branch")
           locals.add(y.getLocalDir()) 
        }
        String[] arrayurls = urls.toArray(new String[urls.size()]);
        String[] arraylocals = locals.toArray(new String[locals.size()]);
        def newscm2 = new hudson.scm.SubversionSCM( arrayurls,  arraylocals)
        newjob.setScm(newscm2)    

        
       }

    }  
  }
}