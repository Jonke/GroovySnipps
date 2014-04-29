def str_search = "Celeste"
def str_replace = "Dawn"


def setTop(job, str_search, str_replace){
	println(job.getName())
     def pl = job.getPublishersList()
           
         
           def bt = pl.grep(hudson.tasks.BuildTrigger.class) 
        
          println("BT " +bt )
     for(aa in bt){
            println("aa " + aa.getChildProjectsValue())
              def n= aa.getChildProjectsValue()
             
               n = n.replace(str_search, str_replace)
                pl.remove(aa)
               pl.add( new hudson.tasks.BuildTrigger(n, false)) 
              }
                   
    
    }

def setRepos(newjob){
 def csm = newjob.getScm()
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

def hi = hudson.model.Hudson.instance
def project = hi.getItem("Ulysses_Celeste_Top")
 newTopName = project.getName().replace(str_search, str_replace)

 def topjob = Hudson.instance.copy(project, newTopName)
 
   println(project.displayName)
   def di = project.getUpstreamProjects()
   println("up")
     for ( x in di ) {
     
      println(x.name)


  //create the new project name
  newName = x.getName().replace(str_search, str_replace)

 
   println(newName)
  // copy the job, disable and save it
  def job = Hudson.instance.copy(x, newName)
  job.disabled = true
  job.save()
setTop(job,str_search, str_replace)
setRepos(job)

     }

 hi.rebuildDependencyGraph()