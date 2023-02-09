package dev.vince.publisher.executable.impl;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.project.MavenProject;

import dev.vince.publisher.executable.api.AbstractMojoExecutable;

public final class MavenJavadocExecutable extends AbstractMojoExecutable {    
    public MavenJavadocExecutable(final MavenProject mavenProject, final MavenSession mavenSession, final BuildPluginManager buildPluginManager) {
        super(mavenProject, mavenSession, buildPluginManager, "org.apache.maven.plugins", "maven-javadoc-plugin", "2.9.1", "jar");
    }
}
