# Background

While developing a tool to edit JASS code files, I encountered the need to extract and import JASS files from MPQ archives. The only existing Java project which can do this is JMPQ3, however it rebuilds the entire MPQ in order to add files, which does not work for my use case. It's open source, so I do eventually want to add this functionality to the JMPQ3 project.

In the meantime, I wanted a way to add this feature quickly. Existing MPQ libraries such as stormlibsharp exist, so I decided to make a very simple C# project which can be interfaced with on a command line, which will then by executed by the Java code. This was why I created FrozenMPQ

# Usage

Download the release.zip or build the project yourself. You need to have "FrozenMPQ.exe", "listfile.txt", "StormLibSharp.dll", "StormLib.dll", "FrozenMPQ.dll", "FrozenMPQ.runtimeconfig.json", and your map file in the same directory.

Run the application as such:
./FrozenMPQ.exe <action> <mpq> <file1> <file2> ... <fileX>
  
The action can either be "ext" or "imp" (extract or import).
The mpq should be a relative path to your MPQ file
You can list 1 or more files to extract/import.

If you specify extract, then it will extract the exact name from the MPQ archive into the "out" folder. For example extracting "war3map.w3u" and "scripts\war3map.j" would result in two files going into the "out" folder, one of them in the root folder and one in the scripts folder.

If you specify import, then it will import the specified files from the "in" folder. For example importing "war3map.w3u" and "scripts\war3map.j" would expect those two files to exist in the "in" folder, one in root directory and one in the "scripts" folder.

Detailed logging will be printed out to the console.

All file paths are relative to the FrozenMPQ.exe path.

# Dependency Informtation

This application uses a customized version of StormLibSharp ( https://github.com/robpaveza/stormlibsharp )

Stormlibsharp uses Stormlib ( https://github.com/ladislav-zezula/StormLib )

Full credit to Zezula for his MPQ editing code.

# Build information

Clone project and add StormLibSharp.dll as a dependency in Visual Studio

Build as an x86 platform target
