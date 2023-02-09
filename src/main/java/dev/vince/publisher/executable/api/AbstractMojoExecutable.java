package dev.vince.publisher.executable.api;

import static org.twdata.maven.mojoexecutor.MojoExecutor.artifactId;
import static org.twdata.maven.mojoexecutor.MojoExecutor.configuration;
import static org.twdata.maven.mojoexecutor.MojoExecutor.executeMojo;
import static org.twdata.maven.mojoexecutor.MojoExecutor.executionEnvironment;
import static org.twdata.maven.mojoexecutor.MojoExecutor.goal;
import static org.twdata.maven.mojoexecutor.MojoExecutor.groupId;
import static org.twdata.maven.mojoexecutor.MojoExecutor.plugin;
import static org.twdata.maven.mojoexecutor.MojoExecutor.version;

import java.util.ArrayList;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.twdata.maven.mojoexecutor.MojoExecutor.Element;

public abstract class AbstractMojoExecutable {
    private final MavenProject mavenProject;
    private final MavenSession mavenSession;
    private final BuildPluginManager pluginManager;
    
    private final ArrayList<Element> configuration;

    private final String groupId;
    private final String artifactId;
    private final String version;
    private final String goal;
    
    protected AbstractMojoExecutable(final MavenProject mavenProject, final MavenSession mavenSession, final BuildPluginManager buildPluginManager, final String groupId, final String artifactId, final String version, final String goal, final Element... configuration) {
        this.mavenProject = mavenProject;
        this.mavenSession = mavenSession;
        this.pluginManager = buildPluginManager;

        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.goal = goal;
        this.configuration = new ArrayList<>();

        for(final Element element : configuration) {
            this.configuration.add(element);
        }
    }
    
    public void execute() throws MojoExecutionException {
        executeMojo(
            plugin(
                groupId(groupId),
                artifactId(artifactId),
                version(version)
            ),
            goal(goal),
            configuration(
                configuration.toArray(new Element[configuration.size()])
            ),
            executionEnvironment(
                mavenProject,
                mavenSession,
                pluginManager
            )
        );
    }
}
