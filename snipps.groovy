Process p = "dir".execute()
println "${p.text}"

Process p = "ghc --version".execute()
println "${p.text}"


Process p = "svnversion".execute()
println "${p.text}"


Process p = "ghc-pkg list".execute()
println "${p.text}"     