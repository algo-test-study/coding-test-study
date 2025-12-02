def solution(record):
    answer = []
    nicknames = {}
    commands = []
    
    for r in record:
        command = r.split()
        
        if command[0] != 'Leave':
            nicknames[command[1]] = command[2]
        if command[0] == 'Change':
            continue
        commands.append((command[0], command[1]))
    
    for command, uid in commands:
        nickname = nicknames[uid]
        if command == 'Leave':
            answer.append(nickname+ '님이 나갔습니다.')
        elif command == 'Enter':
            answer.append(nickname+ '님이 들어왔습니다.')
    
    return answer
