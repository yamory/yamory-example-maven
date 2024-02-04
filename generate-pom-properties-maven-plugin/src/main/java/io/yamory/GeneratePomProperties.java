package io.yamory;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.apache.maven.artifact.Artifact;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

@Mojo(name = "generatePomProperties", requiresDependencyResolution = ResolutionScope.RUNTIME)
public class GeneratePomProperties extends AbstractMojo {

    @org.apache.maven.plugins.annotations.Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException {
        String pomBaseDir = project.getBasedir() + "/.pom";
        File pomBaseDirFile = new File(pomBaseDir);
        if (pomBaseDirFile.exists()) {
            deleteFilesRecursively(pomBaseDirFile);
        }

        Set<Artifact> artifacts = project.getArtifacts();
        for (Artifact artifact : artifacts) {
            String groupId = artifact.getGroupId();
            String artifactId = artifact.getArtifactId();
            String version = artifact.getVersion();

            String pomDirPath = pomBaseDir + "/META-INF/maven/" + groupId + "/" + artifactId;
            File pomDir = new File(pomDirPath);
            pomDir.mkdirs();

            File file = new File(pomDir, "pom.properties");
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("groupId=" + groupId + "\n");
                writer.write("artifactId=" + artifactId + "\n");
                writer.write("version=" + version + "\n");
            } catch (IOException e) {
                throw new MojoExecutionException("Error writing pom.properties", e);
            }
        }
    }

    private boolean deleteFilesRecursively(File rootFile) {
        File[] allFiles = rootFile.listFiles();
        if (allFiles != null) {
            for (File file : allFiles) {
                deleteFilesRecursively(file);
            }
        }
        return rootFile.delete();
    }
}
