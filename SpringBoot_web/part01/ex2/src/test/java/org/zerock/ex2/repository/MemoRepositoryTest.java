package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex2.entity.Memo;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() throws Exception {
        //then
        System.out.println(memoRepository.getClass().getName());
     }

    @Test
    public void testInsertDummies() throws Exception {
        //given
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample....." + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() throws Exception {
        //given
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("====================================");
        //when
        if (result.isPresent()) {
            Memo memo = result.get();

            //then
            System.out.println("memo = " + memo);
        }
    }

    @Transactional
    @Test
    public void testSelect2() throws Exception {
        //given
        Long mno = 99L;

        Memo memo = memoRepository.getOne(mno);

        System.out.println("====================================");

        //then
        System.out.println("memo = " + memo);
    }

    @Test
    public void testUpdate() throws Exception {
        //given
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();

        //then
        System.out.println(memoRepository.save(memo));
    }

    @Test
    public void testDelete() throws Exception {
        //given
        Long mno = 100L;

        //then
        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault() throws Exception {
        //given
        PageRequest pageable = PageRequest.of(0, 10);

        //when
        Page<Memo> result = memoRepository.findAll(pageable);

        //then
        System.out.println("result = " + result);

        System.out.println("------------------------------------------------------");

        // 총 몇 페이지
        System.out.println("result.getTotalPages() = " + result.getTotalPages());

        // 전체 개수
        System.out.println("result.getTotalElements() = " + result.getTotalElements());

        // 햔제 페이지 번호 0부터 시작
        System.out.println("result.getNumber() = " + result.getNumber());

        // 페이지당 데이터 개수
        System.out.println("result.getSize() = " + result.getSize());

        // 다음 페이지 존재 여부
        System.out.println("result.hasNext() = " + result.hasNext());

        // 시작 페이지(0) 여부
        System.out.println("result.isFirst() = " + result.isFirst());
    }

    @Test
    public void testSort() throws Exception {
        //given
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample....." + i).build();
            memoRepository.save(memo);
        });

        Sort sort1 = Sort.by("mno").descending();


        Sort sort2 = Sort.by("mno").ascending();

        Sort sortAll = sort1.and(sort2);

        Pageable pageable2 = PageRequest.of(0, 5, sortAll);

        Pageable pageable = PageRequest.of(0, 10, sort1);

        //when
        Page<Memo> result = memoRepository.findAll(pageable2);

        //then
        result.get().forEach(memo -> {
            System.out.println(memo);
        });
    }

    @Test
    public void testQueryMethods() throws Exception {
        //given
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample....." + i).build();
            memoRepository.save(memo);
        });

        //when
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(1L, 5L);

        //then
        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodsPageable() throws Exception {
        //given
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample....." + i).build();
            memoRepository.save(memo);
        });

        //when
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(1L, 5L, pageable);

        //then
        result.get().forEach(System.out::println);
     }

     @Commit
     @Transactional
     @Test
     public void testDeleteQueryMethods() throws Exception {
         //given
         IntStream.rangeClosed(1, 10).forEach(i -> {
             Memo memo = Memo.builder().memoText("Sample....." + i).build();
             memoRepository.save(memo);
         });

         //then
         memoRepository.deleteMemoByMnoLessThan(5L);
      }
}