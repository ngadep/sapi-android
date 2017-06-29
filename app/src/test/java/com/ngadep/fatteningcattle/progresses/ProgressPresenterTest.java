package com.ngadep.fatteningcattle.progresses;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Cow;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class ProgressPresenterTest {
    private static final String COW_ID = "lkIJrJ97hiHJH";
    private static final String PRICE_KEY = "price";
    private static final Long PRICE_VALUE = 49300L;

    private ProgressPresenter mPresenter;

    @Mock
    private ProgressContract.View mView;

    @Mock
    private ProgressRepository mRepository;

    @Captor
    private ArgumentCaptor<BaseRepository.ModelListener<Cow>> mModelCowListenerCaptor;

    @Captor
    private ArgumentCaptor<BaseRepository.SettingListener<Long>> mPriceSettingListenerCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new ProgressPresenter(mView, mRepository, COW_ID);
    }

    @Test
    public void testCreatePresenter_setsThePresenterToView() {
        verify(mView).setPresenter(mPresenter);
    }

    @Test
    public void testStartPresenter() {
        mPresenter.start();

        Cow cow = new Cow("jLLi67yiu", "8687656", "male", 210, 1496412086216L);
        Mockito.verify(mRepository).getCowModelFromId(eq(COW_ID), mModelCowListenerCaptor.capture());
        mModelCowListenerCaptor.getValue().onModelChange(cow);
        assertEquals("Cow Not Equals", mPresenter.getCow(), cow);
        verify(mView).notifyCowChange(cow);

        Mockito.verify(mRepository).getSetting(eq(PRICE_KEY), mPriceSettingListenerCaptor.capture());
        mPriceSettingListenerCaptor.getValue().onValueChange(PRICE_VALUE);
        assertEquals("Price Value Not Equals", mPresenter.getPricePerKg(), PRICE_VALUE);
        verify(mView).notifyPriceChange();

        verify(mRepository).getCowProgressFromId(COW_ID);
        verify(mView).getAllCowProgress(mRepository.getCowProgressFromId(COW_ID));
    }

}