
def solution(video_len, pos, op_start, op_end, commands):
    
    def to_second(time):
        mm, ss = map(int, time.split(":"))
        return mm * 60  + ss
    
    def to_minute(time):
        mm = time // 60
        ss = time % 60
        return f"{mm:02d}:{ss:02d}"
        
    curr = to_second(pos)    
    end_time = to_second(video_len)
    op_start = to_second(op_start)
    op_end = to_second(op_end)
    
    if op_start <= curr <= op_end:
        curr = op_end
    
    for command in commands:
        if command == "prev":
            curr = max(0, curr - 10)
        elif command == "next":
            curr = min(end_time, curr + 10)    
        if op_start <= curr <= op_end:
            curr = op_end

    return to_minute(curr)
