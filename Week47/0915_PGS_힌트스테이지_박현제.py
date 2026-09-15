def solution(cost, hint):
    n = len(cost)
    cnt = n - 1
    size = 1 << cnt
    INF = 10**30

    hint_count = [[0] * n for _ in range(cnt)]

    for b in range(cnt):
        for hint_number in hint[b][1:]:
            hint_count[b][hint_number - 1] += 1

    dp = [INF] * size
    
    dp[0] = 0

    for stage in range(n):
        nxt_dp = [INF] * size

        for mask in range(size):
            if dp[mask] == INF:
                continue

            use_cnt = 0

            for bundle_stage in range(stage):
                if mask & (1 << bundle_stage):
                    use_cnt += hint_count[bundle_stage][stage]

            use_cnt = min(use_cnt, n - 1)
            curr_cost = dp[mask] + cost[stage][use_cnt]

            nxt_dp[mask] = min(nxt_dp[mask], curr_cost)

            if stage < cnt:
                buy_mask = mask | (1 << stage)

                nxt_dp[buy_mask] = min(
                    nxt_dp[buy_mask],
                    curr_cost + hint[stage][0]
                )

        dp = nxt_dp
    ans = min(dp)
    return ans
