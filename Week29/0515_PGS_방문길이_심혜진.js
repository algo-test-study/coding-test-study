function validateLoad(x,y){
    return x<=5&&x >=(-5)&&y<=5&&y >=(-5)
}

function move(x,y,stage){
    if(stage === 'U'){
        return [x,y+1];
    }else if(stage === 'D'){
        return [x,y-1];
    }else if(stage === 'R'){
        return [x+1,y];
    }else if(stage === 'L'){
        return [x-1,y];
    }
}

function solution(dir){
    // let dirArr = dir.split();//'ULURRD'
    let dirArr = dir.split('');
    let store = new Set();
    let [x,y] = [0,0];
    for(let dir of dirArr){
        let [nx,ny] = move(x,y,dir);
        if(!validateLoad(nx,ny)) continue;
        store.add(`${x},${y},${nx},${ny}`);
        store.add(`${nx},${ny},${x},${y}`);
        [x,y]=[nx,ny];
    }

    return store.size/2;
}
