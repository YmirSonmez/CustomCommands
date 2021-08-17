# CustomCommands
CustomCommands Plugin for Nukkit.
You can simply add commands via config.

### Config
```YML
commands:

  - name: "test"
    desc: "First command"
    perm: "mcpe.testperm"
    kill: false
    allies:
      - "testt"
    messages:
      - "Hello world!"
      - "How are you?"
      
  - name: "killme"
    desc: "Second command"
    perm: "bruh.kill"
    kill: true
    allies:
      - "killl"
    messages:
      - "OH NO!"
```

#### Keywords
- kill : Boolean -> True kills command sender -> Default false
- name : String -> Command -> Forced -> Must lowercase
- allies : String List -> Must lowercase
- perm : String -> Command permission
- desc : String -> Command description -> Default "Server Command"
- messages : String List -> Sends the entire list as a chat message
- formContent : ----
