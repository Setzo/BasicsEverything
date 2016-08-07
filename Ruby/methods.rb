def add(x = 1, y = 2, z)
  x + y + z
end

puts add(3).to_s
puts add(5, 4, 3)

def add2(x, *y)
  x + y.reduce { |k, v| k + v }
end

puts add2(1, 2, 3, 4, 5).to_s

puts add2(1, *[2, 3, 4, 5]).to_s

def keywordAdd(x: 1, hhh: 2, **custom)

  x + hhh + [*custom].map { |subArray| subArray[1] }.reduce { |n, v| n + v }
end

puts keywordAdd(x: 3, hhh: 5, sdada: 2, dfssd: 1, das: 4)
