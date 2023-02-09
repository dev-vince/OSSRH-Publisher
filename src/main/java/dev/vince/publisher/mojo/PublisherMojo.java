package dev.vince.publisher.mojo;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Server;
import org.apache.maven.settings.Settings;
import org.apache.maven.settings.building.DefaultSettingsBuilder;
import org.apache.maven.settings.building.DefaultSettingsBuilderFactory;

import dev.vince.publisher.executable.impl.MavenJavadocExecutable;
import dev.vince.publisher.executable.impl.MavenSourceExecutable;

@Mojo(name = "deploy", defaultPhase = LifecyclePhase.COMPILE)
public class PublisherMojo extends AbstractMojo {
    @Component
    private MavenProject mavenProject;

    @Component
    private MavenSession mavenSession;
    
    @Component
    private BuildPluginManager pluginManager;

    @Parameter(property = "gpg.passphrase", required = true)
    String gpgPassphrase;

    @Parameter(property = "ossrh.username", required = true)
    String ossrhUsername;

    @Parameter(property = "ossrh.password", required = true)
    String ossrhPassword;
    
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException{
        final DefaultSettingsBuilderFactory defaultSettingsBuilderFactory = new DefaultSettingsBuilderFactory();
        final DefaultSettingsBuilder defaultSettingsBuilder = defaultSettingsBuilderFactory.newInstance();
        final Settings settings = new Settings();

        final Server server = new Server();
        server.setId("ossrh");
        server.setUsername(ossrhUsername);
        server.setPassword(ossrhPassword);

        settings.addServer(server);
        
        final MavenSourceExecutable mavenSourceExecutable = new MavenSourceExecutable(mavenProject, mavenSession, pluginManager);
        getLog().info("Generating sources for project...");
        mavenSourceExecutable.execute();
        getLog().info("Sources generated!");

        final MavenJavadocExecutable mavenJavadocExecutable = new MavenJavadocExecutable(mavenProject, mavenSession, pluginManager);
        getLog().info("Generating javadoc for project...");
        mavenJavadocExecutable.execute();
        getLog().info("Javadoc generated!");
    }
}
