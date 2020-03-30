FROM kairops/duing

RUN apt-get -y update && \
    apt-get install -y openjdk-11-jdk maven

COPY intellij_install.sh /tmp
RUN /tmp/intellij_install.sh

# tar -czf intellij_assets.tar.gz .config/ .gnome/ .IdeaIC2019.3/  .local/
USER ubuntu
COPY intellij_assets.tar.gz /tmp
RUN tar -xvzf /tmp/intellij_assets.tar.gz -C /home/ubuntu
USER root