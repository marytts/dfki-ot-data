form Calculate offsets between two lists of corresponding WAV files
  sentence WAV_list_1
  sentence WAV_list_2
endform

list1 = Read Strings from raw text file... 'wAV_list_1$'
numFiles1 = Get number of strings
list2 = Read Strings from raw text file... 'wAV_list_2$'
numFiles2 = Get number of strings
assert numFiles1 == numFiles2

output$ = "'shellDirectory$'/offsets.yaml"
filedelete 'output$'

for f to numFiles1
  select list1
  file1$ = Get string... f
  select list2
  file2$ = Get string... f
  snd1 = Read from file... 'file1$'
  snd2 = Read from file... 'file2$'
  if Object_'snd1'.dx < Object_'snd2'.dx
    select snd1
    resampled = Resample... 1/Object_'snd2'.dx 50
    plus snd2
  elsif Object_'snd1'.dx > Object_'snd2'.dx
    select snd2
    resampled = Resample... 1/Object_'snd1'.dx 50
    plus snd1
  endif
  Cross-correlate... "peak 0.99" zero
  offset = Get time of maximum... 0 0 Sinc70
  basename$ = replace_regex$(file1$, ".*/(.+)\.wav$", "\1", 1)
  plus resampled
  plus snd1
  plus snd2
  Remove
  fileappend 'output$' 'basename$': 'offset:6''newline$'
endfor

select list1
plus list2
Remove
