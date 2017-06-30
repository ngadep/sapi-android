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
    private Cow cow = new Cow("jLLi67yiu", "8687656", "male", 210, 1496412086216L);

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

        getCowModel();

        Mockito.verify(mRepository).getSetting(eq(PRICE_KEY), mPriceSettingListenerCaptor.capture());
        mPriceSettingListenerCaptor.getValue().onValueChange(PRICE_VALUE);
        assertEquals("Price Value Not Equals", mPresenter.getPricePerKg(), PRICE_VALUE);
        verify(mView).notifyPriceChange();

        verify(mRepository).getCowProgressFromId(COW_ID);
        verify(mView).getAllCowProgress(mRepository.getCowProgressFromId(COW_ID));
    }

    @Test
    public void  testShowActivity_EditProgressActivityNew() {
        mPresenter.showEditProgressActivity();

        verify(mView).startEditProgressActivity(COW_ID, null);
    }

    @Test
    public void  testShowActivity_EditProgressActivityEdit() {
        // random progress id
        String progressId = "lrEREsLOOKJlk875";
        mPresenter.showEditProgressActivity(progressId);

        verify(mView).startEditProgressActivity(COW_ID, progressId);
    }

    @Test
    public void  testShowActivity_EditCowActivity() {
        mPresenter.start();
        getCowModel();
        mPresenter.showEditCowActivity();

        verify(mView).startEditCowActivity(COW_ID, cow.getPackage_id());
    }

    @Test
    public void testCleanupRepository() {
        mPresenter.cleanup();
        verify(mRepository).cleanup();
    }

    private void getCowModel() {
        Mockito.verify(mRepository).getCowModelFromId(eq(COW_ID), mModelCowListenerCaptor.capture());
        mModelCowListenerCaptor.getValue().onModelChange(cow);
        assertEquals("Cow Not Equals", mPresenter.getCow(), cow);
        verify(mView).notifyCowChange(cow);
    }
}