# Prerequisites

You must have Java installed in order to run this program.

# Usage

To use this program, click on "run.sh". If your computer can't open this file, try renaming it to "run.bat" and running that. If that still does not work, open up the run.sh file as a text document, copy the command, and run it.

# Feature descriptions

## File

### Open
Prompts the user to open a file. This application supports any text-based files, in addition to the following formats:
.wts files: WC3 String files. Can be parsed properly and merged with object files.
.w3a, .w3o, etc.: WC# Object files. Reads the names of the objects and prints it out as a rawcodes file.
If you want to merge .wts and .w3a/o/etc, open the .wts file first and then the .w3a/o
.w3x, .w3m files: WC3 Archives. Extracts critical map files to /tempfiles

### Save
Prompts the user to save.
This application can only save text files.

### About
Opens the about menu, which displays hotkeys and a link to the source code.

### Exit
Exits the application.

## Edit

### Undo
Un-does the last action.

### Redo
Re-does the last undone action.

### Search
Open the search window.
Only searches down the file.

## Modify

### Merge Scripts
### Merge Scripts (Deduplicate)
### Apply JJCP
### Apply JJCP (Deduplicate)
### Apply NZCP
### Apply NZCP (Deduplicate)
### Scramble Names
### Rename Variable
### Rename Function

## Code

### Reformat code
### De-format code
### Optimize GUI Triggers
### Syntax Check code
### Un-hex code

## Utility

### Generate rawcodes
### Extract MPQ
### Compute StringHash
### Break StringHash

## Styles

### Dark Theme
### JASSCraft Theme
### Light Theme

## Function Browser

### Show Function Browser
### Hide Function Browser
### Search for Function
### Clear Function Browser

# Hotkeys

F1: About
F8: Format code
F9: Syntax check
F10: Toggle function browser
Ctrl + Enter: Run Autocomplete
Ctrl + O: Open
Ctrl + S: Save
Ctrl + F: Search
Ctrl + V: Paste
Ctrl + C: Copy
Ctrl + X: Cut
Ctrl + Z: Undo
Ctrl + Y: Redo

# Overwritable files

The following file paths are overwritable. You may add a .j extension to these files, or leave it off.
These files can be deleted without negatively impacting the application.
The purpose of allowing this feature is to make users able to customize the application behavior without changing the code.

cheatpacks/JJCP - The contents of JJ's cheatpack as a single JASS file. Activator should be "wc3edit"
cheatpacks/NZCP - The contents of NZ CP as a single JASS file. Activator should be "easymode"
cheatpacks/FAI - The contents of Fai's cheatpack as a single JASS file.

The following must exist but can be overwritten:

jasshelper/common - The contents of common.j
jasshelper/blizzard - The contents of blizzard.j