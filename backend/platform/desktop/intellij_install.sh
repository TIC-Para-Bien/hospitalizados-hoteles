#!/bin/sh

echo "Installing IntelliJ IDEA..."

# We need root to install
[ $(id -u) != "0" ] && exec sudo "$0" "$@"

# IntelliJ Edition
ed="C"

# Fetch the most recent version
VERSION="2019.3.4"

# Prepend base URL for download
URL="https://download.jetbrains.com/idea/ideaI${ed}-${VERSION}.tar.gz"

echo $URL

# Truncate filename
FILE=$(basename ${URL})

# Set download directory
DEST=/tmp/${FILE}

echo "Downloading idea-I${ed}-${VERSION} to ${DEST}..."

# Download binarydf -h
wget -cO ${DEST} ${URL} --read-timeout=5 --tries=0

echo "Download complete!"

# Set directory name
DIR="/opt/idea-I${ed}-${VERSION}"
``
echo "Installing to ${DIR}"

# Untar file
if mkdir ${DIR}; then
    tar -xzf ${DEST} -C ${DIR} --strip-components=1
fi

# Grab executable folder
BIN="${DIR}/bin"

# Add permissions to install directory
chmod -R +rwx ${DIR}

# Set desktop shortcut path
DESK=/usr/share/applications/IDEA.desktop

# Add desktop shortcut
echo -e "[Desktop Entry]\nEncoding=UTF-8\nName=IntelliJ IDEA\nComment=IntelliJ IDEA\nExec=${BIN}/idea.sh\nIcon=${BIN}/idea.png\nTerminal=false\nStartupNotify=true\nType=Application" -e > ${DESK}

# Create symlink entry
ln -s ${BIN}/idea.sh /usr/local/bin/idea

echo "Done."
