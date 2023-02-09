package dev.vince.publisher.executable.impl;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.project.MavenProject;

import dev.vince.publisher.executable.api.AbstractMojoExecutable;

public final class MavenSourceExecutable extends AbstractMojoExecutable {    
    public MavenSourceExecutable(final MavenProject mavenProject, final MavenSession mavenSession, final BuildPluginManager buildPluginManager) {
        super(mavenProject, mavenSession, buildPluginManager, "org.apache.maven.plugins", "maven-source-plugin", "2.2.1", "jar");
    }
}
