# Favorite

Run my solution on **[repl.it](https://repl.it/@Xevion/A-Computer-Science-February-2015-Favorite)!**

I most definitely complicated this as much as possible, although, the code is still very, very small.

I simply had two literal string primitive arrays setup with team member names and actor names. Actor or Actress could be varied using a expected prefix of `M` (for *actor*) or `F` (for *actress*).

When printing, I simply substring'd it away using a ternary operator with two options (`"actor" : "actress"`), and then output it alongside a `.substring(1)` (which starts at index 1 and includes everything including and following it).

`String.format` comes in handy here when it comes to regulating a more complex set of values quickly, I recommend those who don't use it to get more familiar (it's very, very useful when formatting/rounding floating point numbers off).