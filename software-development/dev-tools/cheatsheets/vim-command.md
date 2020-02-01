# Vim Command

\(resources: [https://vim.fandom.com/wiki/Moving\_lines\_up\_or\_down](https://vim.fandom.com/wiki/Moving_lines_up_or_down)\)

* `+` jump to next line 
* `G` jump to last line of the file
* `ggVG` selects all content. gg moves to first line. V starts visual mode. G jumps to last line thereby selecting from first to last line
* To go to first line of a file press `Esc`and chose one of the following:
  * `gg`, or
  * `:1`, or
  * `1G`, or
  * \`1gg
* `Ctrl o` + 
  * `0` ==&gt; go to start of the line
  * `$` ==&gt; go to end of file
  * `1` + Shift G ==&gt; go to start of the file
  * `o` ==&gt; insert in a new line
  * `a` ==&gt; insert after cursor
  * `A` ==&gt; insert at the of the line
  * `i` ==&gt; insert before cursor
  * `G`+`o` ==&gt; insert at the end of a file
* `vim + myfile.txt` ==&gt; insert at the end of a file \(add an automatic G\)
* `/" dt"` ==&gt; find the first quote and delete till next quote
* `dw` ==&gt; delete word
* `di"` ==&gt; delete word within "
* `da"` ==&gt; delete word within " including the "
* `dG` ==&gt; delete from cursor until the end of the file 
* `d$` ==&gt; delete from cursor until the end of the line
* `x` delete character
* `:s/toReplace/withWord` in the line
* `:%s/toReplace/withWord` in the file
* `:3,4s/toReplace/withWord` between lines
* `Cntr` + `r` ==&gt; redo 

## Visual mode

### Comment in/comment out

To comment in: 

1. Press `Esc`
2. Press `Ctrl-v` 
3. Select lines
4. Press `I` 
5. Insert the character to put at the beginning of the line 
6. Press `Esc`

To comment out: 

1. Press `Esc`
2. press `Ctrl-v`
3. Select lines
4. Press `x` 
5. Press `Esc`

## Select and delete

1. Go to the first line 
2. `V`
3. move down to last line
4. `d`

