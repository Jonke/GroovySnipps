//  pub is the original trigger, j is the project
def t = new DownstreamTrigger(
      (pub.getChildProjectsValue() =~ genericReplacementPattern)
        .replaceAll(genericReplacementText),
      pub.getThreshold(),
      pub.isOnlyIfSCMChanges(),
      pub.getStrategy(),
      pub.getMatrixTrigger())
j.getPublishersList().remove(pub)
j.getPublishersList().add(t)




def hi = hudson.model.Hudson.instance
hi.getItems(hudson.model.Project).each {project ->

  if (project.displayName== "TestTop"){
   println(project.displayName)
   def di = project.getUpstreamProjects()
   def triggers=project.getBuildTriggerUpstreamProjects()
      for (z in triggers){
          println("triggers " + z.getDisplayName())
            
      def pl = z.getPublishersList()
            for(w in pl){
              
        println(w)
         
           def bt = w.grep(hudson.tasks.BuildTrigger.class) 
          
          println(bt )
          for(aa in bt){
            println("BT " + aa.getChildProjectsValue())
               def newtop =  hi.getItem("TestTop2")
                 pl.add( new hudson.tasks.BuildTrigger("TestTop2", false)) 
               graph = Hudson.getInstance().getDependencyGraph();
              newtop.buildDependencyGraph(graph)
              }
             
          }
        //z.buildDependencyGraph()
      }  
     
   

       

    
  }
}



def hi = hudson.model.Hudson.instance
hi.getItems(hudson.model.Project).each {project ->

  if (project.displayName== "TestTop"){
   println(project.displayName)
   def di = project.getUpstreamProjects()
   def triggers=project.getBuildTriggerUpstreamProjects()
      for (z in triggers){
          println("triggers " + z.getDisplayName())
            
      def pl = z.getPublishersList()
            for(w in pl){
              
        println(w)
         
           def bt = w.grep(hudson.tasks.BuildTrigger.class) 
        
          println("BT " +bt )
          for(aa in bt){
            println("aa " + aa.getChildProjectsValue())
               def newtop =  hi.getItem("TestTop2")
                pl.remove(aa)
                 pl.add( new hudson.tasks.BuildTrigger("TestTop2", false)) 
             // def  graph = Hudson.getInstance().getDependencyGraph()
             //newtop.buildDependencyGraph(graph)
              }
             
          }
        //def  graph2 = Hudson.getInstance().getDependencyGraph()
        //z.buildDependencyGraph(graph2)
      
      }  
     
   

       

    
  }
}
println("slut")

def hi = hudson.model.Hudson.instance
hi.rebuildDependencyGraph()









def hi = hudson.model.Hudson.instance
def newtop =  hi.getItem("TestTop2")
  println(newtop.displayName)
  
  def bl = newtop.getBuildersList()
  println(bl)
   def cpl= bl.grep(hudson.plugins.copyartifact.CopyArtifact)
  for(a in cpl){
   println(a) //hudson.plugins.copyartifact.CopyArtifact@b6c502c
   
    def projname= a.getProjectName()

   def buildsel= a.getBuildSelector() 

  def filter =a.getFilter()

    def target=a.getTarget() 

    def isflat=  a.isFlatten() 

     def isopt= a.isOptional() 

println(projname)
     
     def newcopy=new hudson.plugins.copyartifact.CopyArtifact(projname, buildsel,  filter,  target,          isflat, isopt)
     bl.add(newcopy)
     
  }


//clone every job in view
 
def str_view = "MyProduct_Release_1.0"
def str_search = "Rel_1.0"
def str_replace = "Rel_1.1"
 
def view = Hudson.instance.getView(str_view)
 
//copy all projects of a view
for(item in view.getItems())
{

  //create the new project name
  newName = item.getName().replace(str_search, str_replace)

 
  // copy the job, disable and save it
  def job = Hudson.instance.copy(item, newName)
  job.disabled = true
  job.save()
  
  // update the workspace to avoid having two projects point to the same location
  AbstractProject project = job
  def new_workspace = project.getCustomWorkspace().replace(str_search, str_replace)
  project.setCustomWorkspace(new_workspace)
  project.save()
  
  println(" $item.name copied as $newName")

}


//list every job in view
 
def str_view = "Ulysses_Celeste"

 
def view = Hudson.instance.getView(str_view)
 

for(item in view.getItems())
{

  

 println(item.getName())

 

}


Laps_Install_Ulysses_Celeste
BT [hudson.tasks.BuildTrigger@4b3439e5]
aa InfoCare_Ulysses_Celeste, Videojet_Ulysses_Celeste
Test_Framework_Ulysses_Celeste
BT [hudson.tasks.BuildTrigger@5db3d82e]
aa Ulysses_Celeste_Top
Ulysses_Celeste64
BT [hudson.tasks.BuildTrigger@51b104c1]
aa Laps_Install_Ulysses_Celeste
Ulysses_Celeste_HG
BT [hudson.tasks.BuildTrigger@333811dd]
aa Laps_Install_Ulysses_Celeste, InfoCare_Ulysses_Celeste
Ulysses_Celeste_HG_SearchWithoutAbsence
BT [hudson.tasks.BuildTrigger@5ac129bf]
aa InfoCare_Ulysses_Celeste



def hi = hudson.model.Hudson.instance
def project = hi.getItem("Ulysses_Celeste_Top")

 
   println(project.displayName)
   def di = project.getUpstreamProjects()
   println("up")
     for ( x in di ) {
     
      println(x.name)
     }
